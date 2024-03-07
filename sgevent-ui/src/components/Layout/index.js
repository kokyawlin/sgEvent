import * as React from "react";
import Header from "../Header";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";

export default function Layout({ children }) {
  return (
    <Box>
      <Header />
      <br />
      <Container maxWidth="lg">{children}</Container>
    </Box>
  );
}
