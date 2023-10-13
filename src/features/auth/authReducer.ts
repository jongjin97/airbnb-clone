import { createAction, createSlice, PrepareAction } from '@reduxjs/toolkit';
import { RootState } from '../../app/store';
import { persistUser, readUser } from '../../service/localStorage.service';
import { ResponseLogin } from '../../interface/auth';

interface AuthState {
  user: any; // 사용자 정보 형식에 맞게 수정해야 합니다.
}

const initialState: AuthState = {
  user: readUser(),
};

export const setUser = createAction<PrepareAction<ResponseLogin>>('user/setUser', (newUser) =>{
  persistUser(newUser);

  return {
    payload: newUser,
  }
})

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
  },
  extraReducers: (builder) => {
    builder.addCase(setUser, (state, action) => {
      state.user = action.payload;
    })
  }
});

export const selectUser = (state: RootState) => state.auth.user;
const authReducer = authSlice.reducer;

export default authReducer;