import React, { useState, useEffect } from "react";
import { navigate } from "gatsby";
import { useSelector } from "react-redux";
import Layout from "../../components/Layout";
import EventReview from "../../components/EventReview";
import { useGetEventDetailsQuery } from "../../services/event.service";
import { authSelector } from "../../state/auth/slice";
import EditEventForm from "../../components/EditEventForm"; // 确保正确引入

const EventDetailsPage = ({ location }) => {
  const eventId = location.search.substring(1); // 从 location 获取 eventId
  const { userInfo } = useSelector((state) => authSelector(state));
  const [event, setEvent] = useState(null);

  const { data: eventData, isLoading } = useGetEventDetailsQuery(eventId);

  useEffect(() => {
    if (eventData) {
      setEvent(eventData);
    }
  }, [eventData]);

  return (
    <Layout>
      <div>
        {event ? (
          <>
            <EditEventForm value={event} type="view" isChipDisabled />{" "}
            {/* 使用 EditEventForm 展示事件详情 */}
            <br />
            <br />
            <br />
            <EventReview eventId={eventId} userId={userInfo.userId} />{" "}
            {/* 渲染事件评论 */}
          </>
        ) : (
          <p>Loading event details...</p>
        )}
      </div>
    </Layout>
  );
};

export default EventDetailsPage;
