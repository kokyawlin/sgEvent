import React from "react";
import { Typography } from "@mui/material";
import Layout from "../components/Layout";
import Header from "../components/Header/index";

export default function Home() {
  return (
    <div>
      <Header /> {/* Header显示在页面顶部 */}
      <h1>Welcome to Your Dashboard</h1>
      {/* 这里可以根据需要添加更多内容 */}
    </div>
  );
}
