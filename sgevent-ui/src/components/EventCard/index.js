import * as React from "react";
import PropTypes from "prop-types";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Skeleton from "@mui/material/Skeleton";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import { getFormattedTime } from "../../utils";
import Button from "@mui/material/Button";
import img from "../../images/img-placeholder.png";

export default function EventCard({ value }) {
  return (
    <Card sx={{ width: 330, margin: 1 }}>
      <CardContent>
        <Box>
          {value ? (
            <img
              style={{ width: 300, height: 138 }}
              alt={value.eventTitle}
              src={value.eventCover || img}
            />
          ) : (
            <Skeleton variant="rectangular" width={250} height={138} />
          )}

          {value ? (
            <Box sx={{ pr: 2, height: 110 }}>
              <Typography gutterBottom variant="body2">
                {value.eventTitle}
              </Typography>
              <Typography
                display="block"
                variant="caption"
                color="text.secondary"
              >
                {value.eventDesc}
              </Typography>
              <Typography
                display="block"
                variant="caption"
                color="text.secondary"
              >
                {`Event Time: ${getFormattedTime(value.eventStartDt)}`}
              </Typography>
              <Typography
                display="block"
                variant="caption"
                color="text.secondary"
              >
                {`Capacity: 0/${value.eventCapacity}`}
              </Typography>
              <Typography
                display="block"
                variant="caption"
                color="text.secondary"
                sx={{
                  textOverflow: "ellipsis",
                  overflow: "hidden",
                  whiteSpace: "revert-layer",
                  "-webkit-line-clamp": "2",
                  "-webkit-box-orient": "vertical",
                  display: "-webkit-box",
                }}
              >
                {`Location: ${value.eventPlace}`}
              </Typography>
            </Box>
          ) : (
            <Box sx={{ pt: 0.5 }}>
              <Skeleton />
              <Skeleton width="60%" />
            </Box>
          )}
        </Box>
      </CardContent>
      <CardActions>
        <Button variant="contained">Join</Button>
      </CardActions>
    </Card>
  );
}
