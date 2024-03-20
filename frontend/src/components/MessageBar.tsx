import React from "react";
import "./styles/MessageBar.css";

interface MessageBarProps {
  message: string;
}

const MessageBar: React.FC<MessageBarProps> = ({ message }) => {
  return <div className="message-bar">{message}</div>;
};

export default MessageBar;
