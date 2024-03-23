import React from "react";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";

const AdminPageLayout = ({ title, children }) => {
  return (
    <Box>
      <Typography variant="h3" gutterBottom>
        {title}
      </Typography>
      {children}
    </Box>
  );
};

export default AdminPageLayout;
