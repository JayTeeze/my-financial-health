import { Box, Grid } from "@mui/material";
import DashboardModule from "./DashboardModule";
import { useState, useEffect } from 'react';
import axios from 'axios';


const Dashboard = () => {

    const [assets, setAssets] = useState([]);
    const [assetSum, setAssetSum] = useState();
    const [liabilities, setLiabilities] = useState([]);
    const [liabilitySum, setLiabilitySum] = useState();
    const [incomes, setIncomes] = useState([]);
    const [incomeSum, setIncomeSum] = useState();
    const [expenses, setExpenses] = useState([]);
    const [expenseSum, setExpenseSum] = useState();

    const getRequest = (apiUrl, stateFunc) => {
        axios.get(apiUrl).then(response => {
            stateFunc(response.data);
        }).catch(error => {
            console.log(error);
        });
    }

    useEffect(() => {

        getRequest('http://localhost:8080/findAllAssets', setAssets);
        getRequest('http://localhost:8080/sumAssetValues', setAssetSum);
        getRequest('http://localhost:8080/findAllLiabilities', setLiabilities);
        getRequest('http://localhost:8080/sumLiabilityValues', setLiabilitySum);

        getRequest('http://localhost:8080/findAllIncome', setIncomes);
        getRequest('http://localhost:8080/sumIncomeValues', setIncomeSum);
        getRequest('http://localhost:8080/findAllExpenses', setExpenses);
        getRequest('http://localhost:8080/sumExpenseValues', setExpenseSum);
    }, []);
    
    return (
        <Box sx={{ p: 2 }} >
            <Grid container spacing={2}>
                <Grid item xs={12} md={6}>
                    <DashboardModule metrics={assets} metricsSum={assetSum} metricDescription='Asset' />
                </Grid>
                <Grid item xs={12} md={6}>
                    <DashboardModule metrics={liabilities} metricsSum={liabilitySum} metricDescription='Liability' />
                </Grid>
                <Grid item xs={12} md={6}>
                    <DashboardModule metrics={incomes} metricsSum={incomeSum} metricDescription='Income' />
                </Grid>
                <Grid item xs={12} md={6}>
                    <DashboardModule metrics={expenses} metricsSum={expenseSum} metricDescription='Expense' />
                </Grid>
            </Grid>
        </Box>
    )
}

export default Dashboard;