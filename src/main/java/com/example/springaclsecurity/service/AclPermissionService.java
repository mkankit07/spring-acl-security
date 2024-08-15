package com.example.springaclsecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.AccessControlEntry;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AclPermissionService {

    private final JdbcMutableAclService aclService;

    public void assignPermissionToUser(Object domainObject, Integer domainId, String username, Permission permission) {
        ObjectIdentity oid = new ObjectIdentityImpl(domainObject.getClass(), domainId);
        MutableAcl acl = null;
        try {
            acl = (MutableAcl) aclService.readAclById(oid);
        } catch (final NotFoundException nfe) {
            acl = aclService.createAcl(oid);
        }
        Sid sid = new PrincipalSid(username);
        acl.insertAce(acl.getEntries().size(), permission, sid, true);
        aclService.updateAcl(acl);
    }

    public void assignPermissionToAuthority(Object domainObject,Integer domainId, String authority, Permission permission) {
        ObjectIdentity oid = new ObjectIdentityImpl(domainObject.getClass(), domainId);
        MutableAcl acl = null;
        try {
            acl = (MutableAcl) aclService.readAclById(oid);
        } catch (final NotFoundException nfe) {
            acl = aclService.createAcl(oid);
        }

        Sid sid = new GrantedAuthoritySid(authority);
        acl.insertAce(acl.getEntries().size(), permission, sid, true);
        aclService.updateAcl(acl);
    }

    public void assignPermissionToUser(Object domainObject, Integer domainId, String username, Permission permission, Object parentDomainObject, Integer parentDomainId) {
        ObjectIdentity oid = new ObjectIdentityImpl(domainObject.getClass(), domainId);
        ObjectIdentity parentOid = new ObjectIdentityImpl(parentDomainObject.getClass(), parentDomainId);

        MutableAcl parentAcl;
        MutableAcl childAcl;

        try {
            parentAcl = (MutableAcl) aclService.readAclById(parentOid);
        } catch (NotFoundException nfe) {
            parentAcl = aclService.createAcl(parentOid);
        }

        try {
            childAcl = (MutableAcl) aclService.readAclById(oid);
        } catch (NotFoundException nfe) {
            childAcl = aclService.createAcl(oid);
        }

        childAcl.setParent(parentAcl);
        Sid sid = new PrincipalSid(username);
        childAcl.insertAce(childAcl.getEntries().size(), permission, sid, true);
        aclService.updateAcl(parentAcl);
        aclService.updateAcl(childAcl);
    }

    public void revokePermission(Object domainObject,Integer domainId, String username, Permission permission) {
        ObjectIdentity oid = new ObjectIdentityImpl(domainObject.getClass(), domainId);
        MutableAcl acl = (MutableAcl) aclService.readAclById(oid);
        Sid sid = new PrincipalSid(username);
        List<AccessControlEntry> entries = acl.getEntries();

        for (int i = 0; i < entries.size(); i++) {
            AccessControlEntry entry = entries.get(i);
            if (entry.getSid().equals(sid) && entry.getPermission().equals(permission)) {
                acl.deleteAce(i);
                break;
            }
        }

        aclService.updateAcl(acl);
    }
}
