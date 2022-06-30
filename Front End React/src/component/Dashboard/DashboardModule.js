import { Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import NumberFormat from 'react-number-format';

const DashboardModule = (props) => {

    return(
        <TableContainer component={Paper}>
            <Table sx={{ minWidth: 250 }} size="small" aria-label="a dense table">
                <TableHead>
                    <TableRow>
                        <TableCell>{props.metricDescription} Description</TableCell>
                        <TableCell align="right">Value</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                {props.metrics.map((metric) => (
                    <TableRow
                    key={metric.id}
                    hover
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                    <TableCell component="th" scope="row">
                        {metric.description}
                    </TableCell>
                    <TableCell align="right">
                        <NumberFormat value={metric.amount} displayType={'text'} thousandSeparator={true} prefix={'$'} decimalScale={2} fixedDecimalScale={true} />
                    </TableCell>
                    </TableRow>
                ))}
                    <TableRow>
                        <TableCell component="th" scope="row">Total:</TableCell>
                        <TableCell align="right">
                            <NumberFormat value={props.metricsSum} displayType={'text'} thousandSeparator={true} prefix={'$'} decimalScale={2} fixedDecimalScale={true} />
                        </TableCell>
                    </TableRow>
                </TableBody>
            </Table>
        </TableContainer>
    );
};

export default DashboardModule;