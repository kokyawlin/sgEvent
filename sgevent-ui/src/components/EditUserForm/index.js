import * as React from "react";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import MenuItem from "@mui/material/MenuItem";
import { statusOptions } from "../../constants";
import Stack from "@mui/material/Stack";
import Button from "@mui/material/Button";
import LoadingButton from "@mui/lab/LoadingButton";

export default function EditUserForm({
  value,
  roleList,
  onSubmit,
  isUpdating,
}) {
  const [user, setUser] = React.useState();

  React.useEffect(() => {
    setUser(value);
  }, [value]);
  return user ? (
    <Box
      component="form"
      sx={{
        "& .MuiTextField-root": { m: 1, width: "50ch" },
      }}
      noValidate
      autoComplete="off"
    >
      <div>
        <TextField disabled id="user-id" label="User Id" value={user.userId} />
      </div>
      <div>
        <TextField
          id="user-email"
          label="Email Address"
          disabled
          value={user.emailAddress}
        />
      </div>
      <div>
        <TextField
          id="outlined-select-status"
          select
          label="Status"
          value={user.activeStatus}
          helperText="Please select account status"
          onChange={(event) => {
            setUser((prev) => ({ ...prev, activeStatus: event.target.value }));
          }}
        >
          {statusOptions.map((option) => (
            <MenuItem key={option.value} value={option.value}>
              {option.label}
            </MenuItem>
          ))}
        </TextField>
      </div>
      <div>
        <TextField
          id="outlined-select-role"
          select
          label="Role"
          value={user.roleId}
          helperText="Please select role"
          onChange={(event) => {
            setUser((prev) => ({ ...prev, roleId: event.target.value }));
          }}
        >
          {roleList.map((option) => (
            <MenuItem key={option.roleId} value={option.roleId}>
              {option.roleName}
            </MenuItem>
          ))}
        </TextField>
      </div>
      <br />
      <Box sx={{ width: "50ch" }}>
        <Stack spacing={2} direction="row">
          <LoadingButton
            fullWidth
            variant="contained"
            loadingPosition="end"
            loading={isUpdating}
            onClick={() => {
              onSubmit(user);
            }}
          >
            Submit
          </LoadingButton>
          <Button fullWidth variant="outlined">
            Back
          </Button>
        </Stack>
      </Box>
    </Box>
  ) : null;
}
