import React, { useState } from 'react';
import ReactMarkdown from 'react-markdown';

function ChatComponent() {
  const [prompt, setPrompt] = useState('');
  const [chatResponse, setChatResponse] = useState('');

  const askAI = async () => {
    try {
      const response = await fetch(`http://localhost:8080/chat?prompt=${prompt}`)
      const data = await response.text();
      console.log(data);
      setChatResponse(data);
    } catch (error){
      console.log("Error with chat: ",error)
    }
  };


  return (
    <div>

      <input type="text" 
             value={prompt} 
             onChange={(e) => setPrompt(e.target.value)}
              placeholder="Ask anything"/>

    <button onClick={askAI} className='ask-button'>Enter</button>

    <div className='output'>
    <ReactMarkdown>{chatResponse}</ReactMarkdown>
    </div>

    </div >
  )
}

export default ChatComponent;