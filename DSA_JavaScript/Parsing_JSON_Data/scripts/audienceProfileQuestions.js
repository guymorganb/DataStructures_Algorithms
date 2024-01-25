//Ask what do you want to acomplish with this document?

function askAudienceCharacteristics() {
    const audienceCharacteristics = {
        educationalBackground: prompt('What is the educational background of your most important readers?'),
        culturalCharacteristics: prompt('What are the cultural characteristics of your audience?'),
        professionalExperience: prompt('What professional experience does your audience have, including areas of competence or expertise?'),
        jobResponsibility: prompt('What are the job responsibilities of your audience and how will your document help them accomplish it?'),
        personalCharacteristics: prompt('Are there any personal characteristics, such as age or physical disabilities, that you need to consider for your audience?'),
        readingSpeakingPreferences: prompt('What are the reading, speaking, and listening preferences of your audience?'),
        primaryAudience: prompt('Who is the primary audience to whom the communication is directed?'),
        secondaryAudience: prompt('Who makes up the secondary audience that may not directly act on or respond to the document but who need to be aware of it?'),
        tertiaryAudience: prompt('Is there a tertiary audience that might take an interest in the document, such as interest groups, government officials, or the general public?')
    };

    return audienceCharacteristics;
}

function askReaderCharacteristics() {
    const readerCharacteristics = {
        attitudeTowardYou: prompt('What is your reader’s attitude toward you? (e.g., interested, indifferent, or hostile)'),
        attitudeTowardSubject: prompt('What is your reader’s attitude toward the subject of the document?'),
        expectationsAboutDocument: prompt('What are your reader’s expectations about the document, including scope, pattern, detail, and application?'),

        howReaderWillRead: prompt('How will your reader read the document? (e.g., skim, study carefully, test, etc.)'),
        physicalEnvironment: prompt('In what physical environment will your reader read the document? (e.g., indoors, outdoors, lighting conditions, etc.)'),
        readerReadingSkill: prompt('What is your reader’s reading skill and at what level should you be writing? (e.g., technical level, general public, etc.)'),
        digitalPlatforms: prompt('On which digital platforms will your document be read and how can you design the document to facilitate ease of use? (e.g., mobile, desktop, app, website, etc.)')
    };

    return readerCharacteristics;
}

// Call the function to start the Q&A prompts
const readerDetails = askReaderCharacteristics();
console.log(readerDetails);

const askAudienceTypes = [
    {
        PrimaryAudience : "This group consists of the direct recipients of the communication. They are the main focus and are directly addressed in the document. These individuals or entities are expected to act upon the information provided. In a business setting, this could be a client for whom a proposal is being prepared.",
        SecondaryAudience: "These are the individuals or groups who will not directly act on or respond to the document but who need to be aware of its content. They may use the information to support the primary audience or for their own purposes. An example might be the management team of a company that oversees the primary audience.",
        TertiaryAudience: "This audience consists of people who might take an indirect interest in the communication, such as interest groups, government officials, or the general public. They may not be directly involved with the actions taken by the primary and secondary audiences but may be affected by the decisions or need to be informed. For instance, this could include regulatory bodies that monitor the compliance of the document's content.",
    }
]
// Call the function to start the Q&A prompts
const audienceDetails = askAudienceCharacteristics();
console.log(audienceDetails);
