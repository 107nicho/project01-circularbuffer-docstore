/* THIS FILE IS COMPLETE. DO NOT MODIFY */

/**
 * Represents a document in the document store.
 * Documents are immutable after creation.
 */
public class Document {
    private final String docId;
    private final String text;
    private final long timestamp;

    /**
     * Creates a new document.
     *
     * @param docId     unique identifier for this document
     * @param text      the document content
     * @param timestamp creation time in milliseconds since epoch
     */
    public Document(String docId, String text, long timestamp) {
        if (docId == null || text == null) {
            throw new IllegalArgumentException("docId and text cannot be null");
        }
        this.docId = docId;
        this.text = text;
        this.timestamp = timestamp;
    }

    /**
     * Convenience constructor that uses current time as timestamp.
     *
     * @param docId unique identifier for this document
     * @param text  the document content
     */
    public Document(String docId, String text) {
        this(docId, text, System.currentTimeMillis());
    }

    public String getDocId() {
        return docId;
    }

    public String getText() {
        return text;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Document{" +
                "docId='" + docId + '\'' +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return docId.equals(document.docId);
    }

    @Override
    public int hashCode() {
        return docId.hashCode();
    }
}
