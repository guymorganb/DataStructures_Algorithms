/**
 * Generate UUIDv5 hash using Node Cypto
 */
import { createHash } from 'crypto';

function generateUUIDv5(name, namespace) {
    if (!isHexString(namespace)) {
        throw new Error('The namespace must be a valid hexadecimal string.');
    }
    if (!name) {
        throw new Error('The name must be a non-empty string.');
    }

    const sha1 = createHash('sha1');    // Import the crypto module and create a SHA-1 hash object
    const nsBytes = Buffer.from(namespace.replace(/-/g, ''), 'hex');    // Convert the namespace UUID from a string to bytes
    const nameBytes = Buffer.from(name);    // Convert the name to bytes

    sha1.update(nsBytes);   // Update the hash with the namespace bytes
    sha1.update(nameBytes); // Update the hash with the name bytes
    const hash = sha1.digest(); // Calculate the SHA-1 hash

    const bytes = Buffer.allocUnsafe(16);   // Create a buffer to hold the UUID bytes
    hash.copy(bytes, 0, 0, 16); // Copy the first 16 bytes of the hash into the UUID buffer

    bytes[6] &= 0x0f; // Clear the version bits
    bytes[6] |= 0x50; // Set the version bits to 5 (UUIDv5)
    bytes[8] &= 0x3f; // Clear the variant bits
    bytes[8] |= 0x80; // Set the variant bits to RFC4122

    const result = bytes.toString('hex').match(/(.{8})(.{4})(.{4})(.{4})(.{12})/); // Format the UUID bytes as a UUID
    return `${result[1]}-${result[2]}-${result[3]}-${result[4]}-${result[5]}`; // Return the generated UUIDv5
}

function isHexString(str) {
    return /[0-9a-f]{8,40}/.test(str);
}

// Example usage
const MY_NAMESPACE = '1b671a64-40d5-491e-99b0-da01ff1f3341'; // Example namespace

const uuid = generateUUIDv5('Name ', MY_NAMESPACE); // Generate the UUIDv5
console.log("1 " + uuid); // Output the generated UUIDv5

try {
    const uuid1 = generateUUIDv5('Name ', MY_NAMESPACE);
    console.log("2 " + uuid1);
} catch (error) {
    console.log(error);
}

/**
 MY_NAMESPACE: The namespace is a UUID that serves as a unique identifier for a particular namespace or context. It helps ensure the uniqueness of the generated UUID within that namespace. You can choose any valid UUID for the namespace, but it should remain constant within your application.

name: The name is a string or identifier that you want to associate with the generated UUID. It represents the specific name or entity for which you want to create a UUID. It should be unique within the given namespace.
*/

/**
 UUIDv6

 import { createHash } from 'crypto';

function generateUUIDv6(timestamp, clockSequence, nodeId) {
    if (!timestamp) {
        throw new Error('The timestamp must be a valid timestamp.');
    }
    if (!clockSequence) {
        throw new Error('The clock sequence must be a valid clock sequence.');
    }
    if (!nodeId) {
        throw new Error('The node ID must be a valid node ID.');
    }

    const msb = timestamp >>> 32;
    const lsb = timestamp & 0xFFFFFFFF;

    const bytes = Buffer.allocUnsafe(16);
    bytes[0] = 0x6; // Version 6
    bytes[1] = msb >> 24;
    bytes[2] = (msb >> 16) & 0xFF;
    bytes[3] = (msb >> 8) & 0xFF;
    bytes[4] = msb & 0xFF;
    bytes[5] = lsb >> 24;
    bytes[6] = (lsb >> 16) & 0xFF;
    bytes[7] = (lsb >> 8) & 0xFF;
    bytes[8] = lsb & 0xFF;
    bytes[9] = clockSequence >> 24;
    bytes[10] = (clockSequence >> 16) & 0xFF;
    bytes[11] = (clockSequence >> 8) & 0xFF;
    bytes[12] = clockSequence & 0xFF;
    bytes[13] = nodeId >> 24;
    bytes[14] = (nodeId >> 16) & 0xFF;
    bytes[15] = (nodeId >> 8) & 0xFF;
    bytes[16] = nodeId & 0xFF;

    return `${bytes.toString('hex')}`;
}

// Example usage
const TIMESTAMP = 1656314370517;
const CLOCK_SEQUENCE = 12345;
const NODE_ID = '00:11:22:33:44:55';

const uuid = generateUUIDv6(TIMESTAMP, CLOCK_SEQUENCE, NODE_ID);
console.log(uuid);
 */