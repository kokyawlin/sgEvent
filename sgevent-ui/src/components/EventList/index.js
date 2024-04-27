import React, { useEffect } from "react";
import {
  useGetEventListQuery,
  useDeleteEventMutation,
  useRegisterEventMutation,
} from "../../services/event.service";
import EventCard from "../EventCard";
import Box from "@mui/material/Box";
import { navigate } from "gatsby";

const EventList = ({ isAdmin }) => {
  const { data, error, isLoading, refetch } = useGetEventListQuery(null, {
    refetchOnMountOrArgChange: true,
  });
  const [deleteEvent, deleteResult] = useDeleteEventMutation();
  const [joinEvent, joinResult] = useRegisterEventMutation();

  useEffect(() => {
    if (joinResult.isSuccess) refetch();
  }, [joinResult]);

  const onEdit = (eventId) => {
    navigate(`/events/edit?id=${eventId}`);
  };

  // Add the onDetails function
  const onDetails = (eventId) => {
    navigate(`/events/details?id=${eventId}`);
  };

  return (
    <Box
      sx={{
        flexGrow: 1,
        display: "flex",
        justifyContent: "space-between",
        flexWrap: "wrap",
      }}
    >
      {data?.map((item) => (
        <EventCard
          key={item.eventId}  // Ensure you have a key for list items
          value={item}
          onDelete={deleteEvent}
          isDeleteing={deleteResult.isLoading}
          onEdit={onEdit}
          onJoin={joinEvent}
          onDetails={onDetails}  // Pass onDetails as a prop
          isAdmin={isAdmin}
        />
      ))}
    </Box>
  );
};

export default EventList;
