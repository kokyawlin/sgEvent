import React, { useEffect } from "react";
import Layout from "../../components/Layout";
import AdminPageLayout from "../../components/AdminPageLayout";
import {
  useGetEventDetailsQuery,
  useUpdateEventMutation,
} from "../../services/event.service";
import { navigate } from "gatsby";
import EditEventForm from "../../components/EditEventForm";

export default function EditEvent({ location }) {
  const params = new URLSearchParams(location.search);
  const eventId = params.get("id");

  const { data, error, isLoading } = useGetEventDetailsQuery(eventId);
  const [updateEvent, result] = useUpdateEventMutation();

  useEffect(() => {
    if (result.isSuccess) navigate("/events");
  }, [result.isSuccess]);

  const onUpdateUser = (event) => {
    updateEvent(event);
  };
  return (
    <Layout isLoading={isLoading}>
      <AdminPageLayout title="Edit Event">
        <EditEventForm
          isEdit
          value={data}
          onSubmit={onUpdateUser}
          isUpdating={result.isLoading}
          isError={result.isError}
        />
      </AdminPageLayout>
    </Layout>
  );
}
