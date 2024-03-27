import * as React from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import IconButton from "@mui/material/IconButton";
import EditIcon from "@mui/icons-material/Edit";
import { useGetUserListQuery } from "../../services/user.service";
import { navigate } from "gatsby";

export default function BasicTable() {
  const { data, error, isLoading } = useGetUserListQuery();
  const onEditClick = (emailAddress) => {
    navigate(`/users/edit?emailAddress=${emailAddress}`);
  };
  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>User ID</TableCell>
            <TableCell align="right">UserName</TableCell>
            <TableCell align="right">Status</TableCell>
            <TableCell align="right">Email</TableCell>
            <TableCell align="right">Role</TableCell>
            <TableCell align="right">Actions</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {data?.map((row) => (
            <TableRow
              key={row.userId}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {row.userId}
              </TableCell>
              <TableCell align="right">{row.userName}</TableCell>
              <TableCell align="right">{row.activeStatus}</TableCell>
              <TableCell align="right">{row.emailAddress}</TableCell>
              <TableCell align="right">{row.roleId}</TableCell>
              <TableCell align="right">
                <IconButton
                  color="primary"
                  aria-label="Edit User"
                  onClick={() => {
                    onEditClick(row.emailAddress);
                  }}
                >
                  <EditIcon />
                </IconButton>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
