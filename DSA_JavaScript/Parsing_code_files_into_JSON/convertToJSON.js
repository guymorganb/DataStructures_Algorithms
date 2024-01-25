// this algorithm reads all your files and turns them into JSON format

const fs = require('fs-extra');
const path = require('path');

async function convertJavaFilesToJson(directoryPath) {
  try {
    const categories = [];

    const dirNames = await fs.readdir(directoryPath);

    for (const dirName of dirNames) {
      const dirPath = path.join(directoryPath, dirName);
      const stat = await fs.stat(dirPath);

      if (stat.isDirectory()) {
        const category = { name: dirName, files: [] };

        const fileNames = await fs.readdir(dirPath);
        for (const fileName of fileNames) {
          if (path.extname(fileName) === '.java') {
            const filePath = path.join(dirPath, fileName);
            const codeFileContent = await fs.readFile(filePath, 'utf8');
            category.files.push({ fileName, code: codeFileContent });
          }
        }

        categories.push(category);
      }
    }

    // Save the jsonData to a JSON file in the 'utils' folder
    const outputFilePath = path.join(__dirname, 'utils', 'output.json');
    await fs.writeFile(outputFilePath, JSON.stringify({ categories }, null, 2));
    console.log('Data converted and saved to output.json successfully!');
  } catch (error) {
    console.error('Error reading files or saving data:', error);
  }
}

const directoryPath = '/Users/guymorganb/Desktop/GitHub_Repos/Java_Repo/_Java_/assests/materialsLearned/eclipse-workspace/Fundamentals/src';
convertJavaFilesToJson(directoryPath);


// mirrors the directory with the .java files
async function convertJavaFiles(directoryPath) {
  try {
    const dirNames = await fs.readdir(directoryPath);

    for (const dirName of dirNames) {
      const dirPath = path.join(directoryPath, dirName);
      const stat = await fs.stat(dirPath);

      if (stat.isDirectory()) {
        const outputDir = path.join(__dirname, 'utils', dirName);
        await fs.ensureDir(outputDir); // Make sure the directory exists
        const fileNames = await fs.readdir(dirPath);

        for (const fileName of fileNames) {
          if (path.extname(fileName) === '.java') {
            const filePath = path.join(dirPath, fileName);
            const codeFileContent = await fs.readFile(filePath, 'utf8');
            const outputFile = path.join(outputDir, fileName);
            await fs.writeFile(outputFile, codeFileContent);
          }
        }
      }
    }

    console.log('Java files copied successfully!');
  } catch (error) {
    console.error('Error reading files or saving data:', error);
  }
}

const sourcePath = '/Users/guymorganb/Desktop/GitHub_Repos/Java_Repo/_Java_/assests/materialsLearned/eclipse-workspace/Fundamentals/src';
convertJavaFiles(sourcePath);

