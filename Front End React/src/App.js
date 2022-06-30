import AppRoutes from './routes';
import { ThemeProvider } from '@mui/material';
import customTheme from './component/Theme/Theme';
import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';

const App = () => {
  return (
    <LocalizationProvider dateAdapter={AdapterDateFns}>
      <ThemeProvider theme={customTheme}>
        <AppRoutes />
      </ThemeProvider>
    </LocalizationProvider>
  );
}

export default App;
