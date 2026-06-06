import { test, expect } from '@playwright/test';

test('shows the login screen', async ({ page }) => {
  await page.goto('/');
  await expect(page.getByText('Sign in')).toBeVisible();
  await expect(page.getByRole('button', { name: 'Login' })).toBeVisible();
});

test('shows error when fields are empty', async ({ page }) => {
  await page.goto('/');
  await page.getByRole('button', { name: 'Login' }).click();
  await expect(page.getByText('Please enter both username and password.')).toBeVisible();
});