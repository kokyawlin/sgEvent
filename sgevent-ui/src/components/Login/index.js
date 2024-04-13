import React, { useEffect, useState, useCallback } from "react";
import TextField from "@mui/material/TextField";
import { Grid, Typography, Button } from "@mui/material";
import { useLoginMutation } from "../../services/auth.service";
import { authSelector } from "../../state/auth/slice";
import { navigate } from "gatsby";
import { useSelector } from "react-redux";

export default function Login() {
  const [requestLogin, { isError }] = useLoginMutation();
  const { isLoggedIn } = useSelector((state) => authSelector(state));
  const [emailAddress, setEmailAddress] = useState("");
  const [password, setPassword] = useState("");

  const onLogin = useCallback(() => {
    requestLogin({ emailAddress, password });
  }, [emailAddress, password]);

  useEffect(() => {
    if (isLoggedIn) {
      navigate("/home");
    }
  }, [isLoggedIn]);

  return isLoggedIn ? null : (
    <Grid
      container
      spacing={2}
      justifyContent="center"
      alignItems="center"
      columnSpacing={{ xs: 1, sm: 2, md: 3 }}
    >
      <Grid item xs={12} lg={7} justifyContent="center" alignItems="center">
        <Typography variant="h4" component="h4">
          welcome to community event center.
        </Typography>
      </Grid>
      <Grid item xs={12} lg={7} justifyContent="center" alignItems="center">
        <TextField
          required
          fullWidth
          id="outlined-required"
          label="Required"
          onChange={(e) => setEmailAddress(e.target.value)}
        />
      </Grid>
      <Grid item xs={12} lg={7} justifyContent="center" alignItems="center">
        <TextField
          required
          fullWidth
          id="outlined-required"
          label="Required"
          type="password"
          onChange={(e) => setPassword(e.target.value)}
        />
      </Grid>
      <Grid item xs={12} lg={7} justifyContent="center" alignItems="center">
        <Button variant="contained" onClick={onLogin}>
          Login
        </Button>
        {isError && (
          <Typography color="error">Login failed. Please try again.</Typography>
        )}
      </Grid>
      {/* 在这里添加注册链接 */}
      <Grid item xs={12} lg={7} justifyContent="center" alignItems="center">
        <Typography style={{ marginTop: 20 }}>
          Don't have SG EventHub account?
          <span
            style={{ textDecoration: "underline", cursor: "pointer" }}
            onClick={() => navigate("/signup")}
          >
            Sign Up now
          </span>
        </Typography>
      </Grid>
    </Grid>
  );
}
