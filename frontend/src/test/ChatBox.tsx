import { Client, Message } from "@stomp/stompjs";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useAppSelector } from "src/app/hooks";
import Avatar from "src/components/Avatar";
import { ChatRoom, chatMessage } from "src/interface/chat";


interface ChatBoxProps {
  currChat: ChatRoom;
}
const ChatBox:React.FC<ChatBoxProps> = ({currChat}) => {
    const [messages, setMessages] = useState<chatMessage[]>(currChat.messages);
    const [messageInput, setMessageInput] = useState<string>('');
    const [client, setClient] = useState<Client>();
    const user = useAppSelector((state) => state.auth.user);

    useEffect(() => {
        const newClient = new Client({
          brokerURL: 'ws://localhost:8080/chat',
          debug: (str) => {
            console.log("debug: " + str);
          },
        });
    
        const handleMessageReceived = (message: Message) => {
            console.log(message);
            console.log(JSON.parse(message.body));
          const newMessages = [...messages, JSON.parse(message.body)];
          setMessages(newMessages);
        };
    
        newClient.onConnect = () => {
          newClient.subscribe(`/sub/chat/${currChat?.id}`, handleMessageReceived);
        };
    
        newClient.activate();
        setClient(newClient); // 클라이언트 설정
    
        return () => {
          if (newClient && newClient.active) {
            newClient.deactivate();
          }
        };
      }, [currChat?.id, messages]);
    
      const handleSendMessage = () => {
        const message = messageInput.trim();
        if (message !== '' && client && client.active) {
          client.publish({
            destination: `/pub/chat/${currChat?.id}`,
            body: JSON.stringify({ chatRoomId: currChat?.id, senderId: user, content: message }),
          });
          setMessageInput('');
        }
      };
    return (
        <div className="w-full px-5 flex flex-col justify-between">
            <div className="flex flex-col mt-5">
            {messages.map((message) => (
                message.senderId.id == user?.id ? (
                <div className="flex justify-end mb-4">
                    <div
                    className="mr-2 py-3 px-4 bg-blue-400 rounded-bl-3xl rounded-tl-3xl rounded-tr-xl text-white"
                    >
                    {message.content}
                    </div>
                    <Avatar src={null} size={50}/>
                </div>
                ) : (
                <div className="flex justify-start mb-4">
                <Avatar src={null} size={50}/>
                <div
                    className="ml-2 py-3 px-4 bg-gray-400 rounded-br-3xl rounded-tr-3xl rounded-tl-xl text-white"
                >
                    {message.content}
                </div>
                </div>
                )
            ))}
            </div>
            <div className="py-5 flex flex-row ">
            <input
                className="w-full bg-gray-300 py-5 px-3 rounded-xl"
                type="text"
                placeholder="type your message here..."
                onChange={(value) => {setMessageInput(value.target.value)
                console.log(messageInput)
                console.log(user);
                }}
                value={messageInput}
            />
            <button className="w-20 bg-gray-300 py-5 px-3 rounded-xl" onClick={handleSendMessage}> send </button>
            </div>
        </div>
    );
};

export default ChatBox;