import React, { useState, useEffect } from 'react';
import { useGetEventReviewsQuery, usePostEventReviewMutation } from '../../services/review.service'; // 假设你有这样的服务
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';

const EventReview = ({ eventId, userId }) => {
  const { data: reviews, isLoading } = useGetEventReviewsQuery(eventId);
  const [postReview, { isSuccess }] = usePostEventReviewMutation();

  const [rating, setRating] = useState(0);
  const [comment, setComment] = useState('');

  const handleSubmit = async () => {
    await postReview({ eventId, userId, rating, comment });
  };

  useEffect(() => {
    if (isSuccess) {
      // reset form or show success message
    }
  }, [isSuccess]);

  return (
    <Box>
      <Typography variant="h6">Event Reviews</Typography>
      {isLoading ? (
        <p>Loading reviews...</p>
      ) : (
        reviews.map(review => (
          <Box key={review.review_id}>
            <Typography>{review.comment}</Typography>
            <Typography>Rating: {review.rating}</Typography>
          </Box>
        ))
      )}
      {isUserRegistered && (
        <Box component="form" onSubmit={handleSubmit}>
          <TextField
            label="Rating"
            type="number"
            value={rating}
            onChange={(e) => setRating(Number(e.target.value))}
            required
          />
          <TextField
            label="Comment"
            multiline
            rows={4}
            value={comment}
            onChange={(e) => setComment(e.target.value)}
            required
          />
          <Button type="submit">Submit Review</Button>
        </Box>
      )}
    </Box>
  );
};

export default EventReview;
