import { createTheme } from "@mui/material";

const customTheme = createTheme({
    components: {
        MuiAppBar: {
            styleOverrides: {
                root: {
                    backgroundColor: '#00949c'
                }
            }
        },
        MuiTableCell: {
            styleOverrides: {
                head: {
                    fontWeight: 'bold',
                    backgroundColor: '#fafafa'
                }
            }
        },
        MuiButton: {
            styleOverrides: {
                textSizeLarge: {
                    fontFamily: 'Segoe UI',
                    fontSize: '1.15rem'
                }
            }
        }
    }
});

export default customTheme;