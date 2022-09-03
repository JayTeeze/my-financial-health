import * as React from 'react';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import NumberFormat from 'react-number-format';

export default function SummaryCard({ financialItems }) {

  const sum = () => {
    if (financialItems.length > 1) {
      return financialItems.map(item => item.amount).reduce((prev, curr) => prev + curr);
    } else if (financialItems.length === 1) {
      return financialItems[0].amount;
    } else {
      return 0;
    }
  };

  return (
    <Box>
      <Card>
        <CardContent>
            <Typography>
              Total:
            </Typography>
            <Typography variant='h5' color="text.success">
              {<NumberFormat value={sum()} displayType={'text'} thousandSeparator={true} prefix={'$'} decimalScale={2} fixedDecimalScale={true} />}
            </Typography>
        </CardContent>
      </Card>
    </Box>
  );
}