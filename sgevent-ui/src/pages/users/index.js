import React from "react";
import { Box } from "@mui/material";
import Layout from "../../components/Layout";
import AdminPageLayout from "../../components/AdminPageLayout";
import UserList from "../../components/UserList";

export default function UserPage() {
  return (
    <Layout>
      <AdminPageLayout title="Manage Users">
        <Box sx={{ flexGrow: 1 }}>
          <UserList />
        </Box>
      </AdminPageLayout>
    </Layout>
  );
}
