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
          value={item}
          onDelete={deleteEvent}
          isDeleteing={deleteResult.isLoading}
          onEdit={onEdit}
          onJoin={joinEvent}
          isAdmin={isAdmin}
        />
      ))}
    </Box>
  );
};

export default EventList;
