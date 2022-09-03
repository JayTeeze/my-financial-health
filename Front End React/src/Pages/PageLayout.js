import { Box, Grid } from "@mui/material";
import SummaryCard from "../component/Card/SummaryCard";
import EnhancedTable from "../component/Table/PageTable";
import * as React from 'react';
import { useLocation } from 'react-router-dom';
import { getRequest } from "../component/Service/APIClient";


const PageLayout = () => {
    const [financialItems, setFinancialItems] = React.useState([]);
    const catMap = {assets: 'asset', liabilities: 'liability', income: 'income', expenses: 'expense'};
    let category = useLocation().pathname.replace('/', '');
    
    React.useEffect(() => {
        getRequest(`/findUserFinancialsByCategory?id=1&category=${catMap[category]}`).then(res => setFinancialItems(res));
    }, [category]);

    return(
        <Box sx={{ p:2 }}>
            <Grid container spacing={2} direction={{ xs: "column-reverse", lg: "row" }} >
                <Grid item xs={12} lg={6}>
                    <EnhancedTable category={category} financialItems={financialItems} setFinancialItems={setFinancialItems} />
                </Grid>
                <Grid item xs={2} lg={2}>
                    <SummaryCard financialItems={financialItems} />
                </Grid>
            </Grid>
        </Box>
    );
}

export default PageLayout;