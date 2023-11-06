import { httpApi } from './http.api';

export async function createChatRoom(userId: string) {
  httpApi.post(`/chatroom/${userId}`).then((result) => {
    return result;
  });
}

export async function getMyChatRoom(): Promise<any> {
  return httpApi.get(`/chatroom`);
}
