import * as React from "react";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import MenuItem from "@mui/material/MenuItem";
import { statusOptions } from "../../constants";

export default function EditUserForm({ value, roleList }) {
  return (
    <Box
      component="form"
      sx={{
        "& .MuiTextField-root": { m: 1, width: "50ch" },
      }}
      noValidate
      autoComplete="off"
    >
      <div>
        <TextField
          disabled
          id="user-id"
          label="User Id"
          defaultValue={value.userId}
        />
      </div>
      <div>
        <TextField
          id="user-email"
          label="Email Address"
          disabled
          defaultValue={value.emailAddress}
        />
      </div>
      <div>
        <TextField
          id="outlined-select-status"
          select
          label="Status"
          defaultValue={value.activeStatus}
          helperText="Please select account status"
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
          id="outlined-select-status"
          select
          label="Status"
          defaultValue={value.activeStatus}
          helperText="Please select account status"
        >
          {statusOptions.map((option) => (
            <MenuItem key={option.value} value={option.value}>
              {option.label}
            </MenuItem>
          ))}
        </TextField>
      </div>
    </Box>
  );
}
