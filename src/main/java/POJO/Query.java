/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

/**
 *
 * @author mehdi
 */
public class Query {
    int ownerId;
    int applicantId;
    String encryptedKeyword;

    public Query(int ownerId, int applicantId, String encryptedKeyword) {
        this.ownerId = ownerId;
        this.applicantId = applicantId;
        this.encryptedKeyword = encryptedKeyword;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public String getEncryptedKeyword() {
        return encryptedKeyword;
    }
    
    
}
