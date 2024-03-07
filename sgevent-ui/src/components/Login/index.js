import React, { useEffect } from "react";
import TextField from "@mui/material/TextField";
import { Grid, Typography, Button } from "@mui/material";
import { useLoginMutation } from "../../services/auth.service";
import { authSelector } from "../../state/auth/slice";
import { navigate } from "gatsby";
import { useSelector } from "react-redux";

export default function Login() {
  const [requestLogin, { data, isSuccess, isLoading }] = useLoginMutation();
  const { userInfo, isLoggedin } = useSelector((state) => authSelector(state));

  const onLogin = () => {
    requestLogin();
  };

  useEffect(() => {
    if (userInfo.username || (isSuccess && data.data.username)) {
      navigate("/home");
    }
  }, [isSuccess, userInfo, data]);

  return isLoggedin ? null : (
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
          defaultValue="Username"
        />
      </Grid>
      <Grid item xs={12} lg={7} justifyContent="center" alignItems="center">
        <TextField
          required
          fullWidth
          id="outlined-required"
          label="Required"
          type="password"
          defaultValue="Username"
        />
      </Grid>
      <Grid item xs={12} lg={7} justifyContent="center" alignItems="center">
        <Button variant="contained" onClick={onLogin}>
          Login
        </Button>
      </Grid>
    </Grid>
  );
}
