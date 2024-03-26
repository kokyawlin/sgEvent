import React from "react";
import { Box } from "@mui/material";
import Layout from "../../components/Layout";
import AdminPageLayout from "../../components/AdminPageLayout";
import EditUserForm from "../../components/EditUserForm";
import {
  useGetUserDetailsQuery,
  useUpdateUserMutation,
} from "../../services/user.service";
import { useGetRoleListQuery } from "../../services/role.service";

export default function EditUser({ location }) {
  const params = new URLSearchParams(location.search);
  const userId = params.get("userId");
  // console.log(userId);
  const { data, error, isLoading } = useGetUserDetailsQuery(userId);
  const { data: roleList, isLoading: isRoleLoading } = useGetRoleListQuery();
  const [updateUser, result] = useUpdateUserMutation();
  console.log(data, roleList);

  const onUpdateUser = (user) => {
    updateUser(user);
  };
  return (
    <Layout isLoading={isLoading || isRoleLoading}>
      <AdminPageLayout title="Edit Users">
        <EditUserForm
          value={data}
          roleList={roleList}
          onSubmit={onUpdateUser}
          isUpdating={result.isLoading}
        />
      </AdminPageLayout>
    </Layout>
  );
}
