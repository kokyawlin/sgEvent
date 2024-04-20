import * as React from "react";
import Header from "../Header";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import FormSkeleton from "../FormSkeleton";
import IconButton from "@mui/material/IconButton";
import HomeIcon from '@mui/icons-material/Home';
import { navigate } from "gatsby";

export default function Layout({ children, isLoading }) {
  const handleNavigateHome = () => {
    navigate('/login'); // 修改为你的主页路由
  };

  return (
    <Box>
      <Header />



      <br />
      <Container>{isLoading ? <FormSkeleton /> : children}</Container>
    </Box>
  );
}
