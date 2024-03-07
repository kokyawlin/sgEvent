import React from "react";
import { Box } from "@mui/material";
import Layout from "../components/Layout";
import Login from "../components/Login";

export default function LoginPage() {
  return (
    <Layout>
      <Box sx={{ flexGrow: 1 }}>
        <Login />
      </Box>
    </Layout>
  );
}
