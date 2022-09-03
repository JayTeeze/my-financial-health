import { Box, Grid } from "@mui/material";
import DashboardModule from "./DashboardModule";

const Dashboard = () => {
    
    return (
        <Box sx={{ p: 2 }} >
            <Grid container spacing={2}>
                <Grid item xs={12} md={6}>
                    <DashboardModule category='Asset' />
                </Grid>
                <Grid item xs={12} md={6}>
                    <DashboardModule category='Liability' />
                </Grid>
                <Grid item xs={12} md={6}>
                    <DashboardModule category='Income' />
                </Grid>
                <Grid item xs={12} md={6}>
                    <DashboardModule category='Expense' />
                </Grid>
            </Grid>
        </Box>
    )
}

export default Dashboard;