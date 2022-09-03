import { Box, IconButton, Drawer, Tooltip, TextField, FormControl, InputLabel, OutlinedInput, InputAdornment, Grid, Typography, Button } from "@mui/material";
import * as React from 'react';
import AddIcon from '@mui/icons-material/Add';
import { DatePicker } from "@mui/x-date-pickers";
import { format } from 'date-fns';
import { postRequest } from "../Service/APIClient";
import { useNavigate } from 'react-router-dom';
import SimpleSnackbar from './../Snackbar/Snackbar';


export default function TempRightDrawer({ category }) {
  const catMap = {assets: 'asset', liabilities: 'liability', income: 'income', expenses: 'expense'};
  const initialItem = {userId: 1, description: '', amount: Number(), transactionDate: new Date()};
  const [drawerState, setDrawerState] = React.useState({right: false});
  const [financialItem, setFinancialItem] = React.useState(initialItem);
  const navigate = useNavigate();
  
  const toggleDrawer = (open) => (event) => {
    if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
      return;
    }
    setDrawerState({ ...drawerState, right: open });
  };

  const changeHandler = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    const tempFinancialItem = {...financialItem};

    tempFinancialItem[name] = value;
    setFinancialItem(tempFinancialItem);
  };

  const submitHandler = () => {
    if (financialItem.description !== null && financialItem.amount !== null) {
      const finalItem = {...financialItem,
        amount: parseInt(financialItem.amount),
        category: catMap[category],
        transactionDate: format(financialItem.transactionDate, "yyyy-MM-dd")};

      postRequest('/createFinancialEntry', finalItem);
      // navigate(0);
    }
    SimpleSnackbar('test');
    setDrawerState({ ...drawerState, right: false }); // Fix
  };

  const addItem = () => (
    <Box
      component="form"
      sx={{
        '& .MuiFormControl-root': { m: 1, width: '40ch' },
        '& .MuiButton-fullWidth': { m: 1, width: "95%" }
      }}
      role="presentation"
    >
      <Grid item xs={12} lg={12}>
        <Typography variant="h4" align="center">{category}</Typography>
      </Grid>
      <Grid>
        <Grid item xs={12} lg={12}>
          <TextField
            id="outlined-basic"
            label="Title"
            name="title"
            value={financialItem.title}
            onChange={changeHandler}
            autoFocus={true}
          />
        </Grid>
        <Grid item xs={12} lg={12}>
          <TextField
            id="outlined-basic"
            label="Description"
            name="description"
            value={financialItem.description}
            onChange={changeHandler}
          />
        </Grid>
        <Grid item xs={12} lg={12}>
          <FormControl>
            <InputLabel htmlFor="outlined-adornment-amount">Amount</InputLabel>
            <OutlinedInput
              id="outlined-adornment-amount"
              name="amount"
              value={parseInt(financialItem.amount)}
              onChange={changeHandler}
              startAdornment={<InputAdornment position="start">$</InputAdornment>}
              label="Amount"
              type="number"
            />
          </FormControl>
        </Grid>
        <Grid item xs={12} lg={12} sx={{ height: "100%" }}>
          <DatePicker 
            label="Date added.."
            name="date"
            value={financialItem.transactionDate}
            onChange={(newDate) => {setFinancialItem({ ...financialItem, transactionDate: newDate })}}
            // onChange={(newDate) => {
            //   setDate(newDate);
            // }}
            disableFuture={true}
            renderInput={(params) => <TextField {...params} />}
          />
        </Grid>
        <Grid item xs={12} lg={12}>
          <Button
            variant="contained"
            color={category === "assets" || category === "income" ? "success" : "error"}
            onClick={() => submitHandler()}
            fullWidth={true}
          >
            Add
          </Button>
        </Grid>
      </Grid>
    </Box>
  );

  return (
    <div>
        <React.Fragment>
            <Tooltip title="Add">
                <IconButton
                    edge="start"
                    aria-label="menu"
                    onClick={toggleDrawer(true)}
                >
                    <AddIcon />
                </IconButton>
            </Tooltip>
            <Drawer
            anchor="right"
            open={drawerState.right}
            onClose={toggleDrawer(false)}
            >
                {addItem()}
            </Drawer>
        </React.Fragment>
    </div>
  );
}