import React, { useEffect, useState } from "react";
import Layout from "../../components/Layout";
import AdminPageLayout from "../../components/AdminPageLayout";
import {
  useGetEventDetailsQuery,
  useUpdateEventMutation,
  useRegisterEventMutation,
} from "../../services/event.service";
import { navigate } from "gatsby";
import EditEventForm from "../../components/EditEventForm";
import Button from "@mui/material/Button";

export default function EditEvent({ location }) {
  const params = new URLSearchParams(location.search);
  const eventId = params.get("id");

  const { data, error, isFetching, refetch } = useGetEventDetailsQuery(eventId);
  const [updateEvent, result] = useUpdateEventMutation();
  const [registerEvent, registerResult] = useRegisterEventMutation();
  const [type, setType] = useState("view");
  const [value, setValue] = useState({});

  useEffect(() => {
    if (result.isSuccess) navigate("/events");
  }, [result.isSuccess]);

  useEffect(() => {
    if (registerResult.isSuccess) {
      const userId = registerResult.originalArgs.userId;
      setValue((prev) => {
        const userList = [...prev.userList];
        const index = userList.findIndex((user) => user.userId === userId);
        userList.splice(index, 1);
        return { ...prev, userList };
      });
    }
  }, [registerResult]);

  useEffect(() => {
    setValue(data);
  }, [data]);

  const onUpdateUser = (event) => {
    updateEvent(event);
  };
  return (
    <Layout isLoading={isFetching}>
      <AdminPageLayout
        title={type === "view" ? "Event Details" : "Edit Event"}
        rightEl={
          <Button
            variant="contained"
            onClick={() => {
              setType((prev) => (prev === "view" ? "edit" : "view"));
            }}
          >
            {type === "view" ? "Edit Event Info" : "View Event Details"}
          </Button>
        }
      >
        <EditEventForm
          type={type}
          value={value}
          onSubmit={onUpdateUser}
          onDelete={registerEvent}
          isDeleting={registerResult.isFetching}
          isUpdating={result.isLoading}
          isError={result.isError}
        />
      </AdminPageLayout>
    </Layout>
  );
}
