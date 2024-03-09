import React from "react";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";

const AdminPageLayout = ({ title, children }) => {
  return (
    <Box>
      <Typography variant="h3" gutterBottom>
        {title}
      </Typography>
      <Container>{children}</Container>
    </Box>
  );
};

export default AdminPageLayout;
