import React from "react";
import {
  useGetEventListQuery,
  useDeleteEventMutation,
} from "../../services/event.service";
import EventCard from "../EventCard";
import Box from "@mui/material/Box";
import { navigate } from "gatsby";

const EventList = () => {
  const { data, error, isLoading, refetch } = useGetEventListQuery(null, {
    refetchOnMountOrArgChange: true,
  });
  const [deleteEvent, deleteResult] = useDeleteEventMutation();

  const onEdit = (eventId) => {
    navigate(`/events/edit?id=${eventId}`);
  };

  console.log(data);
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
        />
      ))}
    </Box>
  );
};

export default EventList;
