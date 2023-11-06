import { OtherUser } from './auth';

export interface ChatRoom {
  id: string;
  user: OtherUser;
  user2: OtherUser;
  messages: chatMessage[];
}

export interface chatMessage {
  chatRoomId: string;
  content: string;
  senderId: OtherUser;
}
