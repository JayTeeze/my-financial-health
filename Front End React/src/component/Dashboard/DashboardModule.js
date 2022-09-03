import { Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import NumberFormat from 'react-number-format';
import { useState, useEffect } from 'react';
import { getRequest } from '../Service/APIClient';

const DashboardModule = ({ category }) => {
    const [financialItems, setFinancialItems] = useState([]);
    const limit = 5;
    const emptyRows = financialItems.length >= 0 ? Math.max(0, limit - financialItems.length) : 0;
    const asPlural = {Asset: 'Assets', Liability: 'Liabilities', Income: 'Income', Expense: 'Expenses'}

    useEffect(() => {
        getRequest(`/findUserFinancialsByCategory?id=1&category=${category.toLowerCase()}&limit=${limit}`)
        .then(res => setFinancialItems(res));
    }, []);

    return(
        <TableContainer component={Paper}>
            <Table sx={{ minWidth: 250 }} size="small" aria-label="a dense table">
                <TableHead>
                    <TableRow>
                        <TableCell>{asPlural[category]}</TableCell>
                        <TableCell align="right">Value</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {financialItems.map((financialItem) => (
                        <TableRow
                        key={financialItem.id}
                        hover
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        >
                        <TableCell component="th" scope="row">
                            {financialItem.title}
                        </TableCell>
                        <TableCell align="right">
                            <NumberFormat value={financialItem.amount} displayType={'text'} thousandSeparator={true} prefix={'$'} decimalScale={2} fixedDecimalScale={true} />
                        </TableCell>
                        </TableRow>
                    ))}
                    {emptyRows > 0 && (
                        <TableRow
                            style={{
                            height: (33.02) * emptyRows,
                            }}
                        >
                            <TableCell colSpan={6} />
                        </TableRow>
                    )}
                </TableBody>
            </Table>
        </TableContainer>
    );
};

export default DashboardModule;