import React from "react";
import { Box } from "@mui/material";
import Layout from "../../components/Layout";
import AdminPageLayout from "../../components/AdminPageLayout";
import EditUserForm from "../../components/EditUserForm";
import { useGetUserDetailsQuery } from "../../services/user.service";

export default function EditUser({ location }) {
  const params = new URLSearchParams(location.search);
  const userId = params.get("userId");
  // console.log(userId);
  const { data = {}, error, isLoading } = useGetUserDetailsQuery(userId);
  console.log(data);
  return (
    <Layout isLoading={isLoading}>
      <AdminPageLayout title="Edit Users">
        <Box sx={{ flexGrow: 1 }}>
          <EditUserForm value={data} roleList={[]} />
        </Box>
      </AdminPageLayout>
    </Layout>
  );
}
