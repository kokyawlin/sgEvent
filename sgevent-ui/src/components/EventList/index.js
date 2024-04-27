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
  const { data, error, isFetching, refetch } = useGetEventListQuery(null, {
    refetchOnMountOrArgChange: true,
  });
  const [deleteEvent, deleteResult] = useDeleteEventMutation();
  const [registerEvent, registerResult] = useRegisterEventMutation();

  useEffect(() => {
    if (deleteResult.isSuccess || registerResult.isSuccess) refetch();
  }, [registerResult, deleteResult]);

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
          onRegister={registerEvent}
          isAdmin={isAdmin}
          isRegistering={
            registerResult?.originalArgs?.eventId === item.eventId
              ? registerResult.isLoading || isFetching
              : false
          }
        />
      ))}
    </Box>
  );
};

export default EventList;
