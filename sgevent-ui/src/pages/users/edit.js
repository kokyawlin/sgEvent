import React, { useEffect } from "react";
import { Box } from "@mui/material";
import Layout from "../../components/Layout";
import AdminPageLayout from "../../components/AdminPageLayout";
import EditUserForm from "../../components/EditUserForm";
import {
  useGetUserDetailsQuery,
  useUpdateUserMutation,
} from "../../services/user.service";
import { useGetRoleListQuery } from "../../services/role.service";
import { navigate } from "gatsby";

export default function EditUser({ location }) {
  const params = new URLSearchParams(location.search);
  const emailAddress = params.get("emailAddress");

  const { data, error, isLoading } = useGetUserDetailsQuery(emailAddress);
  const { data: roleList, isLoading: isRoleLoading } = useGetRoleListQuery();
  const [updateUser, result] = useUpdateUserMutation();
  console.log(data, roleList);

  useEffect(() => {
    if (result.isSuccess) navigate("/users");
  }, [result.isSuccess]);

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
          isError={result.isError}
        />
      </AdminPageLayout>
    </Layout>
  );
}
