import React, { useState } from 'react';

function CodeSnippet() {
  const [instructions, setInstructions] = useState('');
  const [language, setLanguage] = useState('');
  const [prompt, setPrompt] = useState('');

  const createSnippet = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/code?instructions=${encodeURIComponent(instructions)}&language=${encodeURIComponent(language)}`
      );
      const data = await response.text();
      console.log(data);
      setPrompt(data);
    } catch (error) {
      console.error("Error with response: ", error);
    }
  };

  return (
    <div>
      <input
        type="text"
        className='instructions'
        value={instructions}
        onChange={(e) => setInstructions(e.target.value)}
        placeholder="What do you want me to create?"
      />

      <input
        type="text"
        className='language'
        value={language}
        onChange={(e) => setLanguage(e.target.value)}
        placeholder="Choose programming language"
      />

      <button onClick={createSnippet}>Enter</button>

      <div className="output">
        <pre className="code-snippet">{prompt}</pre>
      </div>
    </div>
  );
}

export default CodeSnippet;
