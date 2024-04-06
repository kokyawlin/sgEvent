import React from "react";
import { useGetEventListQuery } from "../../services/event.service";
import EventCard from "../EventCard";
import Box from "@mui/material/Box";

const EventList = () => {
  const { data, error, isLoading, refetch } = useGetEventListQuery();
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
        <EventCard value={item} />
      ))}
    </Box>
  );
};

export default EventList;
