import React from "react";
import { Box } from "@mui/material";
import Layout from "../components/Layout";
import UserList from "../components/UserList";

export default function UserPage() {
  return (
    <Layout>
      <Box sx={{ flexGrow: 1 }}>
        <UserList />
      </Box>
    </Layout>
  );
}
