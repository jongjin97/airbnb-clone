import { configureStore } from '@reduxjs/toolkit';
import authReducer from '../features/auth/authReducer';
import errorReducer from '../features/error/errorReducer';
import registerModalReducer from 'src/features/modal/RegisterModalReducer';
export const store = configureStore({
  reducer: {
    auth: authReducer,
    error: errorReducer,
    register: registerModalReducer
  },
  //middleware: [thunk], // Redux Thunk 및 선택적으로 Redux Logger 미들웨어 추가
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;