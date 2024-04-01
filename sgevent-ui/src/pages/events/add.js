import React, { useEffect } from "react";
import Layout from "../../components/Layout";
import AdminPageLayout from "../../components/AdminPageLayout";
import EditEventForm from "../../components/EditEventForm";
import { useAddUserMutation } from "../../services/user.service";
import { useGetRoleListQuery } from "../../services/role.service";
import { navigate } from "gatsby";

export default function AddEvent({ location }) {
  const params = new URLSearchParams(location.search);

  //   const { data: roleList, isLoading: isRoleLoading } = useGetRoleListQuery();
  const [addEvent, result] = useAddUserMutation();

  useEffect(() => {
    if (result.isSuccess) navigate("/users");
  }, [result.isSuccess]);

  const onAddEvent = (user) => {
    addEvent(user);
  };
  return (
    <Layout isLoading={false}>
      <AdminPageLayout title="Add New Event">
        <EditEventForm
          isEdit={false}
          value={{}}
          locationList={[]}
          onSubmit={onAddEvent}
          isUpdating={result.isLoading}
          isError={result.isError}
        />
      </AdminPageLayout>
    </Layout>
  );
}
