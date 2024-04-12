<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\UserFormType;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;

class UserController extends AbstractController
{
    #[Route('/user/new', name: 'user_new', methods: ['GET', 'POST'])]
    public function new(
        Request $request,
        EntityManagerInterface $entityManager,
        FlashyNotifier $flashy
    ): Response {
        $user = new User();
        $form = $this->createForm(UserFormType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // Check if user exists by username or email
            $existingUser = $entityManager->getRepository(User::class)->findOneBy([
                'firstName' => $user->getFirstName(),
                'email' => $user->getEmail(),
            ]);

            if ($existingUser) {
                $flashy->error('User already exists!');
            } else {
                // User does not exist, proceed with creating a new user
                $entityManager->persist($user);
                $entityManager->flush();
                $flashy->success('User created successfully!');
            }

            return $this->redirectToRoute('user_new');
        }

        return $this->render('user/index.html.twig', [
            'form' => $form->createView(),
        ]);
    }
}