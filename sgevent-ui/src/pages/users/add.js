import React, { useEffect } from "react";
import Layout from "../../components/Layout";
import AdminPageLayout from "../../components/AdminPageLayout";
import EditUserForm from "../../components/EditUserForm";
import { useAddUserMutation } from "../../services/user.service";
import { useGetRoleListQuery } from "../../services/role.service";
import { navigate } from "gatsby";

export default function EditUser({ location }) {
  const params = new URLSearchParams(location.search);

  const { data: roleList, isLoading: isRoleLoading } = useGetRoleListQuery();
  const [addUser, result] = useAddUserMutation();

  useEffect(() => {
    if (result.isSuccess) navigate("/users");
  }, [result.isSuccess]);

  const onAddUser = (user) => {
    addUser(user);
  };
  return (
    <Layout isLoading={isRoleLoading}>
      <AdminPageLayout title="Add New User">
        <EditUserForm
          isEdit={false}
          value={{}}
          roleList={roleList}
          onSubmit={onAddUser}
          isUpdating={result.isLoading}
          isError={result.isError}
        />
      </AdminPageLayout>
    </Layout>
  );
}
