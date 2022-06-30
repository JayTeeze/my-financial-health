import { Box, IconButton, Drawer, Tooltip, TextField, FormControl, InputLabel, OutlinedInput, InputAdornment, Grid, Typography, Button } from "@mui/material";
import * as React from 'react';
import AddIcon from '@mui/icons-material/Add';
import { useLocation } from 'react-router-dom';
import axios from 'axios';
import { DatePicker } from "@mui/x-date-pickers";
import { format } from 'date-fns';


export default function TempRightDrawer() {
    const [state, setState] = React.useState({right: false});
    const [financialItem, setFinancialItem] = React.useState({userId: 2, description: '', amount: null});
    const [apiMap, setApiMap] = React.useState({assets: 'createAssetEntry', liabilities: 'createLiabilityEntry', income: 'createIncomeEntry', expenses: 'createExpenseEntry'});
    const [date, setDate] = React.useState(Date.now);

    let location = useLocation();
    let path = location.pathname.replace('/', '');

    const toggleDrawer = (open) => (event) => {
      if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
        return;
      }
  
      setState({ ...state, right: open });
    };

    const changeHandler = (event) => {
      const name = event.target.name;
      const value = event.target.value;
      const tempFinancialItem = {...financialItem};

      tempFinancialItem[name] = value;
      setFinancialItem(tempFinancialItem);
    };

    const submitHandler = (apiUrl) => {
      if (financialItem.description !== null && financialItem.amount !== null) {
        const finalItem = {...financialItem};
        if (path === 'income' || path === 'expense') {
          Object.assign(finalItem, {date: format(date, "yyyy-MM-dd")});
        }
        axios.post(`http://localhost:8080/${apiUrl}`, finalItem).then(response => {
          
        }).catch(error => {
            console.log(error);
        });
      }
      console.log("drawer open: " + state.right);
      toggleDrawer(false);
      console.log("drawer open: " + state.right);
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
          <Typography variant="h4" align="center">Add</Typography>
        </Grid>
        <Grid>
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
                value={financialItem.amount}
                onChange={changeHandler}
                startAdornment={<InputAdornment position="start">$</InputAdornment>}
                label="Amount"
                type="number"
              />
            </FormControl>
          </Grid>
          {path === 'income' || path === 'expense' ? <Grid item xs={12} lg={12} sx={{ height: "100%" }}>
            <DatePicker 
              label="Pick date.."
              value={date}
              onChange={(newDate) => {
                setDate(newDate);
              }}
              renderInput={(params) => <TextField {...params} />}
            />
          </Grid> : null}
          <Grid item xs={12} lg={12}>
            <Button variant="contained" color={path === "assets" || path === "income" ? "success" : "error"} onClick={() => submitHandler(apiMap[path])} fullWidth={true}>
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
                open={state.right}
                onClose={toggleDrawer(false)}
                >
                    {addItem()}
                </Drawer>
            </React.Fragment>
      </div>
    );
}