import { useState } from 'react';
import './App.css';
import ChatComponent from './components/ChatComponent';
import CodeSnippet from './components/CodeSnippet';

function App() {
  const [activeTab, setActiveTab] = useState(() => {
    return localStorage.getItem('activeTab') || 'chat';
  });

  const handleTabChange = (tab) => {
    // alert(tab);         // removed stray 's'
    setActiveTab(tab);
    localStorage.setItem('activeTab', tab);
  };

  return (
    <div className="App">
      <h1>
        Your ultimate<span className='text-gradient'> AI Hub </span> 
        powered by the latest <span className='text-gradient'>OpenAI gpt-oss</span>!
      </h1>

      {/* Note: className matches the CSS below */}
      <div className="buttons">
        <button className={activeTab === 'chat' ? 'active' : ''}
        onClick={() => handleTabChange('chat')}>Chat</button>

        <button  className={activeTab === 'code' ? 'active' : ''}
        onClick={() => handleTabChange('code')}>Generate Code</button>
      </div>

      <div>
        {activeTab === 'chat' && <ChatComponent/>}
        {activeTab === 'code' && <CodeSnippet/>}
      </div>
    </div>
  );
}

export default App;
