import React from "react";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import EventCard from "../../components/EventCard";
import Layout from "../../components/Layout";
import AdminPageLayout from "../../components/AdminPageLayout";
import { navigate } from "gatsby";
export default function EventPage() {
  const onAddClick = () => {
    navigate("/events/add");
  };
  return (
    <Layout>
      <AdminPageLayout
        title="Manage Events"
        rightEl={
          <Button variant="contained" onClick={onAddClick}>
            Add new event
          </Button>
        }
      >
        <Box sx={{ flexGrow: 1 }}>
          <EventCard />
        </Box>
      </AdminPageLayout>
    </Layout>
  );
}
