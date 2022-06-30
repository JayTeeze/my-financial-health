import { Box, Grid } from "@mui/material";
import EnhancedTable from "./PageTable";
import { useState, useEffect } from 'react';
import axios from 'axios';
import { useLocation } from "react-router-dom";


const PageLayout = () => {

    const [financials, setFinancials] = useState([]);
    const [apiMap, setApiMap] = useState({assets: 'findAllAssets', liabilities: 'findAllLiabilities', income: 'findAllIncome', expenses: 'findAllExpenses'});

    let location = useLocation();
    let path = location.pathname.replace('/', '');

    const getRequest = (apiUrl, stateFunc) => {
        axios.get(`http://localhost:8080/${apiUrl}`).then(response => {
            stateFunc(response.data);
        }).catch(error => {
            console.log(error);
        });
    }

    useEffect(() => {

        getRequest(apiMap[path], setFinancials);
    }, [path, financials.length]);

    return(
        <Box sx={{ p:2 }}>
            <Grid container spacing={2}>
                <Grid item xs={12} lg={6}>
                    <EnhancedTable financials={financials} category={path.substring(0, 1).toUpperCase() + path.substring(1)} />
                </Grid>
            </Grid>
        </Box>
    );
}

export default PageLayout;